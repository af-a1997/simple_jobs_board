# Simple jobs board

This is a simple job finder board, where companies would publish their job vacancies and users can find them and apply for whichever one(s) they are interested, users can sign up not only to be able to apply to jobs, but also to manage their own profile and upload their CV.

It was designed as part of a final test for the system analysis and development course I'm doing at [IFSul](http://www.ifsul.edu.br), feel free to play with it, test, use for any needed purpose and suggest improvements.

## How to test?

1) Install [XAMPP](https://www.apachefriends.org/download.html).
2) Clone this repository wherever you like in your filesystem, but please create a dedicated folder for the files, something like `%USERPROFILE%\Java_Devel_WS\jobs_board` for example.
3) Run Apache and MySQL services on XAMPP.
4) Create the `jobs_board_db` database in PMA.
5) Enter to [`http://localhost:9997`](http://localhost:9997) with any browser you like (if this port is busy, change it in [`application.properties`](/src/main/resources/application.properties) and use the same one in the URL.
6) Have fun!

## Notes

* The most important issue is that there are **no** sessions, since we're trying to create multiple roles with their permissions, fetching user data from DDBB and such. In the meantime, all options are available to everyone for the sake of testing functionality of this system.
* Database isn't created automatically, you must create it.
* No sample data is included for now, but I'll try to add that later whenever I can.
* Minor error when enlarging pictures by clicking the link to them is that random text will appear instead.

## Used tools and resources

* Spring Framework.
* Thymeleaf.
* "Black Dashboard" template by Creative Tim.
* Various other resources that were used by the template author such as Popper and Font Awesome icons.
* jQuery and jQuery UI.