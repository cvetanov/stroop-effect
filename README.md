# Stroop effect
User-friendly application developed for testing [The Stroop Effect](https://en.wikipedia.org/wiki/Stroop_effect)

This application is developed in order to achieve a simple stroop effect simulation and as part of my Cognitive Science course.
It consists of two types of simulations:
* Congruent, where the colour of the square and the name of the colour match
* Incongruent, where the name of the colour is different than the colour of the circle

The user's task is to react as quickly as possible and select the square in which the name of the color of the cirle displayed on top is.
*For example, if the circle's colour is red, the user should select the square in which it is written 'Red'*

One test consists of multiple simulations of the two types.

User response time and error percentage are measured and stored into a local SQLite database.

When enough users have tested themselves, the database is pulled from the device using `adb pull`.
The results in the database are later structured and processed in R for confirming the Stroop effect.

An additional feature is the gender of the user. I would like to determine which gender reacts better to this effect.

Here are a few screenshots of the application

*Home screen*

![Home screen](https://cloud.githubusercontent.com/assets/9498471/9978490/30f12edc-5f32-11e5-8b63-d41f790287f4.png)


*Congruent simulation*

![Congruent simulation](https://cloud.githubusercontent.com/assets/9498471/9978489/30ed140a-5f32-11e5-83af-e84e47826c54.png)


*Incongruent simulation with wrong answer chosen*

![Incongruent simulation wrong answer](https://cloud.githubusercontent.com/assets/9498471/9978488/30e7afce-5f32-11e5-97ca-1054d08188ad.png)
