package br.com.devalor.contacts.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showMessage() {
		return "<i>Hello Jersey 2.0</i>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String showMessageJson() {
		return "<i>Hello Jersey 2.0</i>";
	}

}
