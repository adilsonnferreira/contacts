package br.com.devalor.contacts.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devalor.contacts.bean.Contact;
import br.com.devalor.contacts.bean.Email;
import br.com.devalor.contacts.bean.Phone;

@Path("/")
public class ContactsService {

	private static List<Contact> contacts = new ArrayList<Contact>();

	public ContactsService() {

		if (contacts.isEmpty()) {

			Contact contact1 = new Contact();
			contact1.setName("pcmnac ");
			Email email = new Email("casa", "pcmnac@gmail.com");
			contact1.getEmails().add(email);
			Phone phone = new Phone("tim", "8199921448");
			contact1.getPhones().add(phone);
			add(contact1);

			Contact contact2 = new Contact();
			contact2.setName("Lais Monique ");
			Email email2 = new Email("casa", "laismoniquecosta@gmail.com");
			contact2.getEmails().add(email2);
			Phone phone2 = new Phone("tim", "8197335487");
			contact2.getPhones().add(phone2);
			add(contact2);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{contactId}")
	public Contact getContact(@PathParam("contactId") int id) {

		Contact result = null;

		for (Contact contact : contacts) {
			if (contact.getId() == id) {
				result = contact;
				break;
			}
		}

		if (result == null) {
			throw new WebApplicationException(404);
		}

		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> list() {
		return contacts;
	}

	@GET
	@Path("/find/{contactName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> findByName(@PathParam("contactName") String name) {

		List<Contact> result = new ArrayList<Contact>();

		for (Contact contact : contacts) {
			if (contact.getName() != null
					&& contact.getName().toLowerCase()
							.contains(name.toLowerCase())) {
				result.add(contact);
			}
		}

		return result;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Contact add(Contact contact) {

		if (contact.getName() == null || contact.getName().trim().equals("")) {
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST)
					.entity("O nome do contato é obrigatorio").build());
		}

		contacts.add(contact);
		contact.setId(contacts.indexOf(contact) + 1);
		return contact;
	}

	@PUT
	@Path("/{contactId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("contactId") int id, Contact contact) {
		contacts.set(id - 1, contact);
		contact.setId(contacts.indexOf(contact) + 1);
		return Response.ok().build();
	}

	@DELETE
	public Response delete(@QueryParam("contactId") int id) {

		if (id > contacts.size()) {
			throw new WebApplicationException(404);
		}

		contacts.remove(id - 1);
		return Response.ok().build();
	}

}
