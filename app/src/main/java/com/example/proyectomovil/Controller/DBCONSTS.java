package com.example.proyectomovil.Controller;

import com.example.proyectomovil.BuildConfig;

public class DBCONSTS {

    public static String baseRoute = BuildConfig.API_ROUTE;

    // GET
    public static String URL_AUTH = baseRoute + "/api/users/auth/";                 // add phone
    public static String URL_ALL_TRIPS = baseRoute + "/api/trips/all/";
    public static String URL_MY_TRIPS = baseRoute + "/api/trips/mytrips/";          // add userId
    public static String URL_SEATS = baseRoute + "/api/seats/";                     // add tripId

    // POST
    public static String URL_REGISTER_USER = baseRoute + "/api/users/register/";
    public static String URL_REGISTER_SEAT = baseRoute + "/api/seats/register/";
    public static String URL_REGISTER_TRIP = baseRoute + "/api/trips/mytrips/register/";

    // DELETE
    public static String URL_DELETE_SEAT = baseRoute + "/api/seats/drop/";       // add idSeat
    public static String URL_DELETE_MY_TRIP = baseRoute + "/api/trips/mytrips/drop/";

    // PUT
    public static String URL_UPDATE_USER_INFO = baseRoute + "/api/users/update/";

}
