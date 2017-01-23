package com.mscharhag.sparkdemo;
import java.sql.*;

import static com.mscharhag.sparkdemo.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.before;

import model.TargetConnection;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.staticFileLocation;
import Controller.*;
public class Main {


	public static void main(String[] args) {
		TargetConnection targetConnection = TargetConnection.getInstance();
		targetConnection.setCredentials("oracle","ondora02.hu.nl:8521/cursus02.hu.nl","tosad_2016_2b_team3_target","tosad_2016_2b_team3_target");
		before((request, response) -> response.type("application/json"));
		get("/tables", Controller::haaltabellenop, json());
		get("/table/:id/columns", Controller::haalkolommenvantabel, json());
	}



}
