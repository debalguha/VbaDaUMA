<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Index Page</title> <#include "../lib/header.ftl">
</head>
<body>
	<div class="container">
		<#include "../lib/logo-bar.ftl">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<ul class="nav nav-tabs">
						<li><a href="${rc.contextPath}/user/list.do">User</a></li>
						<li><a href="${rc.contextPath}/app/list.do">Team & Application</a></li>
						<li class="active"><a>Report</a></li>
					</ul>
				</div>
			</div>
			<div class="span12">
				<form class="reportForm" role="form" id="reportForm">
				 <fieldset>
					<table class="table">
						<tbody>

							<tr>
	
								<td><input type="radio" name="status" value="active">All Active Users</td>
							</tr>
							<tr>
								<td><input type="radio" name="status" value="inactive">all Inactive Active Users</td>
						        

							</tr>
							<tr>
								<td>
									 All users in the application:   <input class="form-control" id="appName" type="text" name="appName"value="">
								</td>
							</tr>
							<tr>
								<td>
									All users from the team:   <input class="form-control" id="team" type="text" name="team"value="">
								</td>
							</tr>
							
							<tr>
								<td>
									<button type="button" class="btn btn-default" id="reportSubmit">Submit</button>
									<button type="button"  class="btn btn-default" onclick="window.location='';return false;">Cancel</button>
								</td>
							</tr>
						</tbody>
					</table>
					 </fieldset>
				</form>
					<table class="table"  id="resultTb"style=" display: none">
				     	<caption id="tblCaption"></caption>
						<thead>
							<tr>
								
								<th>Username</th>
								<th>status</th>
								<th>FirstName</th>
								<th>LastName</th>
								<th>Phone</th>
								<th>Team</th>
							</tr>
						</thead>
						<tbody id="userInfoBody">
						
							
						</tbody>
					</table>
				
			</div>
		</div>
	</div>	

	<script type="text/javascript" src="${rc.contextPath}/resources/js/report.js?v=${res_version}"></script>
	<#include "../lib/footer.ftl">
</body>
</html>