# SOS Emergencies
***

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Collaboration](#collaboration)
5. [FAQs](#faqs)

### General Info
***
This website is and aid for both practitioners and patients to visualise all medical data.

Patient-side allow each patient to acces their personal data, a history of their previous consultations, the foresight of the upcoming consultation, the ability to modify them and, of course, the ability to make an appointment.

Practitioner-side allow each doctor to access to all patients registered into the database of the facility. Thus, they can add, modify and suppress a patient and their personnal informations. They are also able to see the patient file composed of all his previous consultation and the up-to-come. If the doctor the one weloming the patient, it can modifiy the comment of the consultation until the consultation is over.

Admin-side allow an administrator to add, modify and suppress a practitioner. We imagine that the admin is an IT manager inside the facility and that the practitioners have a local e-mail adress and may ask the IT manager to make changes on their practitioner profile.

## Technologies
***
Git Bash
IntelliJ

## Installation
***
Because the website is hosted locally, you need to import the Website's data onto your computer through an IDE and launch it via a Website deployement tool.

First, in Windows Explorer :
- Create a file dedicated to the website,
- Note down the path to the newly created file

In Git Bash :
- Go to the newly created file :
	`cd C:/the/path/leading/to/your/file`
- Initiate the cloning :
	`git clone https://gitlab.com/hei-projet/hei-projet-2021/sos-emergencies.git`

In IntelliJ :
- Open IntelliJ
- If the "Welcome to IntelliJ IDEA" opens :
	- click on "Open"
	- navigate to `created file > sos-emergency`,
	- click on "OK",
	- on "Trust and Open Maven Project", click on "Trust Project",
	- wait a minute or so that the dependencies and libraries downloads.
- If IntelliJ opens on an existing project :
	- hover on `File > New > Project from Existing Sources...`,
	- navigate to `created file > sos-emergency`,
	- on "Trust and Open Maven Project", click on "Trust Project",
	- wait a minute or so that the dependencies and libraries downloads.
- When all the assets required are downloaded :
	- click on "Add Configuration..." (on the top-right of the screen),
	- click on the "+" in the top-left of the newly opened window,
	- go down the list until reaching "Tomcat" and clock on "local",
	- change the browser to yours default one,
	- go in the "Deployment" tab,
	- click on the "Fix" button (the one with a red lightbulb in the bottom-right corner)
	- select `sos-emergencies:war exploded`,
	- delete everything in the "Application context" text-box,
	- click on "OK",
	- now, click on the green Play button (in the top-right area),
	- wait around a minute for the server to boot.
- The homepage should open on the Web browser you've chosen, welcome to SOS Emergencies !
