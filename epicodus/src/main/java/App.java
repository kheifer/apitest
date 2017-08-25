import com.google.gson.Gson;
import dao.Sql2oAlumniDao;
import dao.Sql2oCohortDao;
import dao.Sql2oStudentDao;
import dao.Sql2oTeacherDao;
import models.Cohort;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

        public static void main(String[] args) {
            staticFileLocation("/public");

            String connector = "jdbc:h2:~/epicodus.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
            Sql2o sql2o = new Sql2o(connector, "", "");
            Sql2oCohortDao cohortDao = new Sql2oCohortDao(sql2o);
            Sql2oAlumniDao alumniDao = new Sql2oAlumniDao(sql2o);
            Sql2oTeacherDao teacherDao = new Sql2oTeacherDao(sql2o);
            Sql2oStudentDao studentDao = new Sql2oStudentDao(sql2o);
            Gson gson = new Gson();

            //Create
            post("/Epicodus/new", "application/json", (request, response) -> {
                Cohort newCohort = gson.fromJson(request.body(), Cohort.class);
                cohortDao.add(newCohort);
                return gson.toJson(newCohort);
            });

            //Read
            get("/Epicodus/all", "application/json", (request, response) -> {
                return gson.toJson(cohortDao.getAll());
            });


            //Create
            post("/Epicodus/:campus/track/new", "application/json", (request, response) -> {
                Track newTrack= gson.fromJson(request.body(), Track.class);
                trackDao.add(newTrack);
                String campus = request.params("campus");
                trackDao.addTrackToCampuses(campus, newTrack.getTrackId());
                return gson.toJson(newTrack);
            });

            //Read
            get("/Epicodus/tracks/all", "application/json", (request, response) -> {
                return gson.toJson(trackDao.getAll());
            });

            get("/Epicodus/:campus/all", "application/json", (request, response) -> {
                String campus = request.params("campus");
                List<Track> campusFinder = trackDao.getAllTracksByLocation(campus);
                if (campusFinder.size() == 0){
                    throw new ApiException(404, String.format("Sorry, looks like the %s campus doesn't have any tracks", request.params("campus")));
                }
                return gson.toJson(campusFinder);
            });

            post("/Epicodus/student/new", "application/json", (request, response) -> {
                Student newStudent= gson.fromJson(request.body(), Student.class);
                studentDao.add(newStudent);
                return gson.toJson(newStudent);
            });

            get("/Epicodus/facts/age", "application/json", (request, response) -> {
                return gson.toJson(studentDao.averageAge());
            });
            get("/Epicodus/facts/gender", "application/json", (request, response) -> {
                return gson.toJson(studentDao.genderDistribution());
            });
            get("/Epicodus/facts/completion", "application/json", (request, response) -> {
                return gson.toJson(studentDao.completion());
            });
            get("/Epicodus/:track/:trackId/students", "application/json", (request, response) -> {
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
}
