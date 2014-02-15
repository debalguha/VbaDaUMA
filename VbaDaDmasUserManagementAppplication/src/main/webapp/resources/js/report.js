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
							var appendHtml = '<tr><td>'+ item.username + '</td><td>'+ item.status+ '</td><td>'+ item.firstName+ '</td><td>'+ item.lastName+ '</td><td>'
							 + item.phone+ '</td><td>';
							 if(item.userTeamAllocations){
								 appendHtml += '<ul>';
								 $.each(item.userTeamAllocations, function(i, teamItem){
									 appendHtml += '<li>'+teamItem.team.name+'</li>';
								 });
								 appendHtml += '</ul>';
							 }
							 appendHtml += '</td></tr>';
							$('#userInfoBody').append(appendHtml);
//							$('#userInfoBody').append('<tr><td>'
//							 + item.username + '</td><td>'
//							 + item.status+ '</td><td>'
//							 + item.firstName+ '</td><td>'
//							 + item.lastName+ '</td><td>'
//							 + item.phone+ '</td><td>'
//							 + item.team.name+ '</td></tr>' );
						});
						//var array = ret.data;
						$('#tblCaption').html(counter+" User/s matched your search.");
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

