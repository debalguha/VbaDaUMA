$(document).ready(function() {


	$('#reportSubmit').click(function(){
		$('#resultTb').hide();
		$('#userInfoBody').html('');
		var team= $('#team').val();
		var appName= $('#appName').val();
		var status = $('input[name=status]:checked').val();
		$.ajax( {
			type : "post",
			url :appContextPath + "/report/runReport.do",

			data : {
				status:status,
				team:team,
				appName:appName,
			},
			dataType : "json",
			success : function(ret) {
				if (ret) {
					$('#resultTb').show();
					if (ret.code == "ok") {
						var counter=0;
						$.each(ret.data, function(i, item){
							counter++;
							$('#userInfoBody').append('<tr><td>'
							 + item.username + '</td><td>'
							 + item.status+ '</td><td>'
							 + item.firstName+ '</td><td>'
							 + item.lastName+ '</td><td>'
							 + item.phone+ '</td><td>'
							 + item.team.name+ '</td></tr>' );
						});
						//var array = ret.data;
						$('#tblCaption').html(counter+" User/s matched your search.");
//						for ( var i = 0; i < array.length; i++) {
//						
//							 $('#userInfoBody').append('<tr><td>'
//									 + array[i].username + '</td><td>'
//									 + array[i].status+ '</td><td>'
//									 + array[i].firstName+ '</td><td>'
//									 + array[i].lastName+ '</td><td>'
//									 + array[i].phone+ '</td><td>'
//									 + array[i].team.name+ '</td></tr>' );
//
//							
//						}

					}else{
						alert("submit Data to Report Action failed ");
					}
				} 
				else {
					alert("submit Data to Report Action failed B");
				}
			},
			error : function() {
				alert("submit Data to Report Action failed C");
			}
		});
	});

	
});

