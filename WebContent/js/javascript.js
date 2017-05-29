/*for showing user home tab content*/


/* submit search and display results */
var form = $('#searchvehicle');
form.submit(function() {

	$.ajax({
		type : form.attr('method'),
		url : form.attr('action'),
		data : form.serialize(),
		success : function(data) {
			var result = data;
			$('#showresult').html(result);
			document.getElementById("showresult").style.display = "table";
			document.getElementById("getVehicles").style.display="table";
			/* $("#showbooking").load("section.jsp #bookingDetails"); */
			if (data
					.search("Sorry..! No result Found") != -1) {
				/* alert("aaaa"); */
				document
						.getElementById("previousVehicle").disabled = true;
				document
						.getElementById("nextVehicle").disabled = true;
			} else {
				/* alert("hghbf"); */
				document
						.getElementById("nextVehicle").disabled = false;
				document
						.getElementById("previousVehicle").disabled = false;
			}
		}
	});

	return false;
});