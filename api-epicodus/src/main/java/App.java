import com.google.gson.Gson;
import dao.Sql2oAlumniDao;
import dao.Sql2oCohortDao;
import dao.Sql2oStudentDao;
import dao.Sql2oTrackDao;
import dataSources.*;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        String connector = "jdbc:h2:~/epicodus.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connector, "", "");
        Sql2oCohortDao cohortDao = new Sql2oCohortDao(sql2o);
        Sql2oTrackDao trackDao = new Sql2oTrackDao(sql2o);
        Sql2oAlumniDao alumniDao = new Sql2oAlumniDao(sql2o);
        Sql2oStudentDao studentDao = new Sql2oStudentDao(sql2o);
        Gson gson = new Gson();

        //Create
        post("/cohorts/new", "application/json", (request, response) -> {
            Cohort newCohort = gson.fromJson(request.body(), Cohort.class);
            cohortDao.add(newCohort);
            return gson.toJson(newCohort);
        });

        //Read
        get("/cohorts/all", "application/json", (request, response) -> {
            return gson.toJson(cohortDao.getAll());
        });


        //Read
        get("/cohorts/:cohortId", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("cohortId"));
            Cohort search = cohortDao.findById(id);
            return gson.toJson(search);
        });
        get("/cohorts/:cohortId/tracks", "application/json", (request, response) -> {
            int cohortId = Integer.parseInt(request.params("cohortId"));
            List<Track> trackShow = trackDao.getAllTracksByCohort(cohortId);
            if (trackShow.size() == 0){
                throw new ApiException(404,"Unfortunately, that cohort doesn't have any tracks set up yet.");
            }
            return gson.toJson(trackShow);
        });


        post("/cohorts/:cohortId/tracks/new", "application/json", (request, response) -> {
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


        post("/tracks/:trackId/students/new", "application/json", (request, response) -> {
            Student newStudent= gson.fromJson(request.body(), Student.class);
            studentDao.add(newStudent);
            return gson.toJson(newStudent);
        });
        get("/tracks/:trackId/students/all", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("trackId"));
            return gson.toJson(studentDao.getAllStudentsByTrack(id));

        });
        get("/tracks/:trackId/students/:id", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            Student search = studentDao.findbyId(id);
            return gson.toJson(search);
        });
        post("/tracks/:trackId/alumni/new", "application/json", (request, response) -> {
            Alumni newAlumni = gson.fromJson(request.body(), Alumni.class);
            studentDao.add(newAlumni);
            return gson.toJson(newAlumni);
        });
        get("/Alumni/all", "application/json", (request, response) -> {
            return gson.toJson(alumniDao.getAllAlumni());
        });
        get("/tracks/:trackId/alumni/:id", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            Alumni search = alumniDao.findById(id);
            return gson.toJson(search);
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
