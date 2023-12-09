package org.libertas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.libertas.bd.AtletaDao; // Certifique-se de ter a classe AtletaDao
import com.google.gson.Gson;

@WebServlet("/Atleta") // Certifique-se de ajustar o caminho conforme necessário
public class Atleta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Atleta() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        AtletaDao adao = new AtletaDao();
        List<org.libertas.bd.Atleta> lista = adao.listar();
        Gson gson = new Gson();
        out.print(gson.toJson(lista));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String body = sb.toString();

            Gson gson = new Gson();
            org.libertas.bd.Atleta a = gson.fromJson(body, org.libertas.bd.Atleta.class);

            AtletaDao adao = new AtletaDao();
            adao.inserir(a);

            Retorno r = new Retorno(true, "registro inserido com sucesso");
            out.print(gson.toJson(r));

        } catch (Exception e) {
            e.printStackTrace();
            Retorno r = new Retorno(false, e.getMessage());
            Gson gson = new Gson();
            out.print(gson.toJson(r));
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String body = sb.toString();

            Gson gson = new Gson();
            org.libertas.bd.Atleta a = gson.fromJson(body, org.libertas.bd.Atleta.class);

            AtletaDao adao = new AtletaDao();
            adao.alterar(a);
            Retorno r = new Retorno(true, "registro alterado com sucesso");
            out.print(gson.toJson(r));

        } catch (Exception e) {
            e.printStackTrace();
            Retorno r = new Retorno(false, e.getMessage());
            Gson gson = new Gson();
            out.print(gson.toJson(r));
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String id = request.getRequestURI();
            id = id.substring(id.lastIndexOf("/") + 1);
            int idInt = (Integer.parseInt(id));

            AtletaDao adao = new AtletaDao();
            adao.excluir(idInt);

            Retorno r = new Retorno(true, "registro excluído com sucesso");
            Gson gson = new Gson();
            out.print(gson.toJson(r));

        } catch (Exception e) {
            e.printStackTrace();
            Retorno r = new Retorno(false, e.getMessage());
            Gson gson = new Gson();
            out.print(gson.toJson(r));
        }

    }
}
