/**
 * 
 */
/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 alert(status);
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidcIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "CustomerAPI",
 type : type,
 data : $("#formcustomer").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onItemSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidcIDSave").val($(this).data("cid"));
 $("#cname").val($(this).closest("tr").find('td:eq(0)').text());
 $("#cphone").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cgender").val($(this).closest("tr").find('td:eq(2)').text());
 $("#cemail").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
{

 $.ajax(
 {
 url : "CustomerAPI",
 type : "DELETE",
 data : "cID=" + $(this).data("cid"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onItemDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#cname").val().trim() == "")
 {
 return "Insert Customer name.";
 }
// NAME
if ($("#cphone").val().trim() == "")
 {
 return "Insert  Phone.";
 } 

// PRICE-------------------------------
if ($("#cgender").val().trim() == "")
 {
 return "Insert Gender.";
 }

// DESCRIPTION------------------------
if ($("#cemail").val().trim() == "")
 {
 return "Insert Email.";
 }
return true;
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 