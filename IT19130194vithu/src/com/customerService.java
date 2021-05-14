package com;

import model.customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/customer")
public class customerService
{
customer cusObj = new customer(); 
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readcustomerDeatils()
 {
 return cusObj.readcustomerdetails();
 }


@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertcustomerdetails(@FormParam("cname") String cname,
 @FormParam("cphone") String cphone,
 @FormParam("cgender") String cgender,
 @FormParam("cemail") String cemail)
{
 String output = cusObj.customerdetails(cname, cphone, cgender, cemail);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String customerdetails(String customerData)
{
//Convert the input string to a JSON object
 JsonObject cusObject = new JsonParser().parse(customerData).getAsJsonObject();
//Read the values from the JSON object
 String cID = cusObject.get("cID").getAsString();
 String cname = cusObject.get("cname").getAsString();
 String cphone = cusObject.get("cphone").getAsString();
 String cgender = cusObject.get("cgender").getAsString();
 String cemail = cusObject.get("cemail").getAsString();
 String output = cusObj.updatecustomerdeatils(cID, cname, cphone, cgender, cemail);
return output;
}


@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletecustomerdetails(String customerData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

//Read the value from the element <cID>
 String cusID = doc.select("cusID").text();
 String output = cusObj.deletecustomerdetails(cusID);
return output;
}

}