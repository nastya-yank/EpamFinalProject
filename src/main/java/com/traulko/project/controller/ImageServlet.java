package com.traulko.project.controller;

import com.traulko.project.util.FileLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * The {@code ImageServlet} class represents image servlet.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = "/image/*")
public class ImageServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String DIRECTORY = "load.location";
    private static final FileLoader fileLoader = new FileLoader();
    private static final String CONTROLLER_PATH = "/controller";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getPathInfo().substring(1);
        String directory = getServletContext().getInitParameter(DIRECTORY);
        Path path = Paths.get(directory, filename);
        response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(CONTENT_LENGTH, String.valueOf(Files.size(path)));
        Files.copy(path, response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String directory = getServletContext().getInitParameter(DIRECTORY);
        Optional<String> fileName = fileLoader.load(request.getParts(), directory);
        String page = (String) request.getSession().getAttribute(RequestParameter.CURRENT_PAGE);
        if (fileName.isPresent()) {
            request.setAttribute(RequestParameter.PHOTO_NAME, fileName.get());
            page = CONTROLLER_PATH;
        } else {
            request.setAttribute(RequestParameter.INCORRECT_IMAGE_MESSAGE, true);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}