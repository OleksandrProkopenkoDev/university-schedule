## First page (welcome page):

#### The user will see a welcome screen and can choose their login role:

- Login as a student
- Login as a teacher There will also be a button to contact the site administrator.

## After logging in as a student:

#### Schedule for the next week:

- A column (square) for each day of the week
- To the left of the item name will be the item number (in order: 1st, 2nd, etc.)
- Lesson names
- Start and end times of the lesson

#### After clicking on a day column, the user will see:

- To the left of the item name will be the item number (in order: 1st, 2nd, etc.)
- Lesson name
- Teacher name
- Start and end times of the lesson
- Description (if the teacher has added it)

## After logging in as a teacher:

#### Schedule for the next week:

- To the left of the item name will be the item number (in order: 1st, 2nd, etc.)
- A column (square) for each day of the week
- Lesson names
- Start and end times of the lesson
- Group (of students) name

#### After clicking on a day column, the teacher will see a menu:

- Description
- Button to change the description
- Button to delete the lesson

#### Add a lesson to the schedule:

- A menu for adding a course that this teacher can add to the schedule
- A menu for adding a group that can be added to this lesson
- A menu for choosing the lesson time (displays only available time slots for the group)
- A field for adding a description for the lesson

## Logic of generating schedule:

#### For students, the schedule will be based on their group, for example:

- Teacher Mr. Garrison added the course "Math" for group A2 on Monday at 2 o'clock, with the description: knowledge test
- Teacher Mrs. Williams added the course "Biology" for group A2 on Monday at 4 o'clock, with the description: laboratory work
- A student from group A2 logs in to the site and sees:
  - In the column "Monday":
  - The string "Math" and the date/time of the lesson
  - The string "Biology" and the date/time of the lesson
  - After clicking on "Math", the student will see a new window with:
    - Lesson name: Math
    - Teacher name: Mr. Garrison
    - Date/time: 2 o'clock
    - Lesson description: knowledge test

#### For teachers, the schedule will be based on lessons that include them as a teacher, for example:
- Teacher Mr. Garrison added the course "Math" for group A2 on Monday at 2 o'clock, with the description: knowledge test
- He will see an updated schedule with the new lesson on Monday at 2 o'clock, and after clicking on the lesson, he can see the description, change it, or delete the entire lesson.
