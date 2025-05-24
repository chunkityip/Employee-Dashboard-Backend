Project: Employee Management System
Goal: Build an internal web application for our company to help manage employees, managers, HR, and admins across departments.
This is an internal tool — clean functionality and clear role separation are more important than polish.

🎯 Who Will Use This System
We have 4 user roles:

Admin – Oversees all users and teams. Can add/remove people and assign roles.

HR – Manages hiring and payments. Can onboard new employees and adjust salary info.

Manager – Leads a team. Assigns work, tracks working hours and progress.

Employee – Regular team member. Views assigned tasks and logs hours.

🛠️ High-Level Features You Need to Build
1. Login & Logout
A simple login page where users enter their username and password.

Once logged in, the user is taken to their dashboard based on their role.

A logout option should bring them back to the login screen and clear their session.

2. Admin Dashboard
View a list of all users and their roles.

Add new users (admin, HR, manager, or employee).

Assign team members to managers.

View a list of departments and who belongs to which one.

3. HR Dashboard
View a list of all employees.

Onboard new employees (name, role, salary, department).

Remove employees if needed.

Edit salaries and payment status (paid/unpaid).

View hiring history and salary reports.

4. Manager Dashboard
View team members.

Assign tasks to each team member with:

Description of task

Hours expected

Story points (work complexity)

Sprint info (e.g., Sprint 1, Sprint 2)

Monitor progress and logged hours for each task.

View historical performance of team members.

5. Employee Dashboard
View assigned tasks (title, description, deadline, sprint).

Log work hours for each task.

Update task status (in progress, done).

See upcoming deadlines and sprint summary.

🗃️ Additional Notes
You’ll store data in a shared company database (hosted in a container).

Everyone sees only what they’re supposed to see:

Managers can’t see payment data.

Employees can’t edit their own roles or salaries.

Login should keep users logged in while they use the app (unless they logout).

📈 Goal of This Project
This is your first full-stack assignment, so we want:

Solid structure and best practices.

Clear navigation and role-based flows.

Clean, working MVP — security will be added later.