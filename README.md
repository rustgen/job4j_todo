#Technical task - project "TODO list"

# Technology stack: Spring boot, Thymeleaf, Bootstrap, Hibernate, PostgreSql

#Description.

1. Schema of the Task table with the fields id, description, created, done. Location /db/
   Upload the script via liquibase.

2. Views.
    - A page with a list of all tasks. The table displays the name, date of creation and status (completed or not)
    - On the page with the list, add the "Add task" button.
    - On the list page, add three links: All, Completed, New. When translating via links, the table should display: all tasks, only completed tasks, or only new ones.
    - When you click on a task, you go to a page with a detailed description.
    - On the page with a detailed description, add buttons: Done, Edit, Delete.
    - If you clicked on the execute button, then the task will be transferred to the status completed.
    - The edit button takes the user to a separate page for editing.
    - Delete button, deletes the task and goes to the list of all tasks.

3. The application must have three layers: Controllers, Services, Persistences.
    - The SessionFactory object is created once in the Main class with the @Bean annotation.
    - The TaskStore object takes a SessionFactory parameter through the constructor.


#Photo