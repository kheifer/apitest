import com.google.gson.Gson;
import dao.*;
import exceptions.ApiException;
import models.*;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

        public static void main(String[] args) {
            staticFileLocation("/public");

            String connector = "jdbc:h2:~/epicodus.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
            Sql2o sql2o = new Sql2o(connector, "", "");
            Sql2oCohortDao cohortDao = new Sql2oCohortDao(sql2o);
            Sql2oTrackDao trackDao = new Sql2oTrackDao(sql2o);
            Sql2oAlumniDao alumniDao = new Sql2oAlumniDao(sql2o);
            Sql2oTeacherDao teacherDao = new Sql2oTeacherDao(sql2o);
            Sql2oStudentDao studentDao = new Sql2oStudentDao(sql2o);
            Gson gson = new Gson();

            //Create
            post("/cohort/new", "application/json", (request, response) -> {
                Cohort newCohort = gson.fromJson(request.body(), Cohort.class);
                cohortDao.add(newCohort);
                return gson.toJson(newCohort);
            });

            //Read
            get("/cohort/all", "application/json", (request, response) -> {
                return gson.toJson(cohortDao.getAll());
            });


            //Read
            get("/cohort/:cohortId", "application/json", (request, response) -> {
                int id = Integer.parseInt(request.params("cohortId"));
                Cohort search = cohortDao.findById(id);
                return gson.toJson(search);
            });
            get("/cohort/:cohortId/track", "application/json", (request, response) -> {
                int cohortId = Integer.parseInt(request.params("cohortId"));
                List<Track> trackShow = trackDao.getAllTracksByCohort(cohortId);
                if (trackShow.size() == 0){
                    throw new ApiException(404,"Unfortunately, that cohort doesn't have any tracks set up yet.");
                }
                return gson.toJson(trackShow);
            });


            post("/cohort/:cohortId/track/new", "application/json", (request, response) -> {
                Track newTrack= gson.fromJson(request.body(), Track.class);
                trackDao.add(newTrack);
                int cohortId = Integer.parseInt(request.params("cohortId"));
                trackDao.addTrackToCohort(cohortId, newTrack.getTrackId());
                return gson.toJson(newTrack);
            });

            //Read
            get("tracks/all", "application/json", (request, response) -> {
                return gson.toJson(trackDao.getAll());
            });


            post("/track/:trackId/student/new", "application/json", (request, response) -> {
                Student newStudent= gson.fromJson(request.body(), Student.class);
                studentDao.add(newStudent);
                studentDao.setTrackId(newStudent, Integer.parseInt(request.params("trackId")));
                return gson.toJson(newStudent);
            });

            get("/students/statistics/popular", "application/json", (request, response) -> {
                return gson.toJson(studentDao.getMostPopularTrack());
            });
            get("/students/statistics/genderstats/:gender", "application/json", (request, response) -> {
                String gender = request.params("gender");
                return gson.toJson(studentDao.genderDistribution(gender));
            });

            get("/students/statistics/completion", "application/json", (request, response) -> {
                return gson.toJson(studentDao.getPercentCompleted ());
            });

            get("/students/statistics/count/:trackId", "application/json", (request, response) -> {
                int trackId = Integer.parseInt(request.params("trackId"));
                return gson.toJson(studentDao.getAllStudentsByTrack(trackId));
            });

            exception(ApiException.class, (exc, req, res) -> {
                ApiException err = exc;
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("status", err.getStatusCode());
                jsonMap.put("errorMessage", err.getMessage());
                res.type("application/json");
                res.status(err.getStatusCode());
                res.body(gson.toJson(jsonMap));
            });


            after((req, res) ->{
                res.type("application/json");
            });
    }
}
