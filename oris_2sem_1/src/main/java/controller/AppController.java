package controller;

import annotations.Controller;
import annotations.GetRequest;
import annotations.PostRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AppController {

    @GetRequest(path="/app")
    public String getAppGet(HttpServletRequest request, HttpServletResponse response) {
        return "Hello!";
    }

    @PostRequest(path="/app")
    public String getAppPost(HttpServletRequest request, HttpServletResponse response) {
        return "Hello!";
    }

    @GetRequest(path="/home")
    public String getHomeGet(HttpServletRequest request, HttpServletResponse response) { return "get Home"; }

    @PostRequest(path="/home")
    public String getHomePost(HttpServletRequest request, HttpServletResponse response) { return "post Home"; }
}
