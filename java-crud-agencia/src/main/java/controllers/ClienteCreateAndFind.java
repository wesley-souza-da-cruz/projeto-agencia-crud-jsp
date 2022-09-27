package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;
import java.util.List;

import dao.ClienteDao;

//Criar

public class ClienteCreateAndFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ClienteCreateAndFind() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pesquisa = request.getParameter("Pesquisa");
		
		if(pesquisa == null) {
			pesquisa="";
		}
		
		List<Cliente> clientes = ClienteDao.find(pesquisa);
		
		request.setAttribute("clientes", clientes);;
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("lista.jsp");
		requestdispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		
		cliente.setNome(request.getParameter("nome"));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setNascimento(request.getParameter("nascimento"));
		cliente.setSituacao(request.getParameter("situacao"));
		
		ClienteDao.create(cliente);
		
		doGet(request, response);
	}

}
