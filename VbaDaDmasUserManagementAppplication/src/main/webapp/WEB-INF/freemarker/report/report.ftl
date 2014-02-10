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
				<form class="form-horizontal" role="form" id="reportForm">
					<div class="control-group">
						<label class="control-label" for="inputStatus_active">All Active Users</label>
					    <div class="controls">
					     	<input id="inputStatus_active" type="radio" name="status" value="active">
					    </div>						
					</div>
					<div class="control-group">
						<label class="control-label" for="inputStatus_inactive">All Active Users</label>
					    <div class="controls">
					     	<input id="inputStatus_inactive" type="radio" name="status" value="inactive">
					    </div>						
					</div>	
					<div class="control-group">
						<label class="control-label" for="appName">All users in the application</label>
					    <div class="controls">
					     	<input class="form-control" id="appName" type="text" name="appName" value="">
					    </div>						
					</div>	
					<div class="control-group">
						<label class="control-label" for="appName">All users from the team</label>
					    <div class="controls">
					     	<input class="form-control" id="team" type="text" name="team" value="">
					     	<div class="controls-row" style="padding-top: 15px;">
					     		<button type="button" class="btn btn-default" id="reportSubmit">Submit</button>
					     	</div>
					    </div>
					</div>	
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