RobotWar
========

ICS4U1 - 
	In my Computer Science Assignment, I have used 4 different algorithms in 3 different types of robots.

![alt tag](http://burakicel.com/wp-content/uploads/2014/08/1.png)


1) TestFighterRobot (Green)
2) TestFighterRobot2 (Blue)
3) IcelBot (White)
4) IcelBot2 (Magenta) (Uses Extended OppData)

TestFighterRobot

	Pros:
	TestFighterRobot is the green robot. And it's strategy is to attack to the closest person. This algorithm can be useful as it allows the robot to attack without losing much energy because it doesn't travel to the further enemies which would decrease robot's energy. 
	
	Cons:
	The robot can attack to powerful enemies just because they are closer to it .This makes a huge disadvantage for this algorithm. This algorithm usually fails when there are strong enemies which are close to the robot that uses this algorithm.

TestFighterRobot2

	Pros:
	TestFighterRobot is the blue robot. And it's strategy is to attack to the person with the least health. This is very useful as it raises the chances of the successful number of the attacks. This allows robot to attack to the weakest enemy, and get the attack bonuses.
	Cons:
	The weakest robot might be further away and this would cause the robot to spend it's energy without much consideration. This also makes one robot target to all robots who use this algorithm. Which makes robot attracted to a hotspot of fights which might be dangerous.
	
	![alt tag](http://burakicel.com/wp-content/uploads/2014/08/2.png)



IcelBot , MasterBot(THE ONE USED FOR BATTLE)

	Pros:
	IcelBot is the white robot. It stores the movements of each robot in oppData. (However does not use it, the one that uses it is IcelBot2). It makes its decision by taking consideration of both health and distance. This is really useful for both attacking a weak and a close robot.

IcelBot2 , Level 4 Robot

	Pros:
	IcelBot2 is takes health, distance to the enemy and the amount of steps that the enemy took (from extended oppData). This allows the IcelBot2 to make smart moves taking a lot of factors into consideration. The idea behind the number of the steps enemy took was to find the enemy who spent most of it's energy.

	Cons:
	IcelBot2 is a really uncertain on whom to attack. The reason behind this is that its affected by many factors. This results in a different target robot every round, which makes IcelBot2 spend a huge energy and not attack for so many rounds and eventually receive penalties.




	Making a Choice :
	In my extended oppData, I stored the number of movements that enemy performed. However this value is not taken into consideration. One might think that adding more factors will make a robot more intelligent however from the results I have gained, it clearly shows that more factors make the target change every round which makes the robot really UNCERTAIN on whom to attack.


BATTLE RESULTS AND THEIR INTERPERATION
========

![alt tag](http://burakicel.com/wp-content/uploads/2014/08/3.png)


Robot Id's


Problems With IcelBot2's :
![alt tag](http://burakicel.com/wp-content/uploads/2014/08/4.png)


These results shows clearly that IcelBot2's got more penalties than normal. Which proves that they are very uncertain robots.

Looking at the winner robot :

![alt tag](http://burakicel.com/wp-content/uploads/2014/08/5.png)
![alt tag](http://burakicel.com/wp-content/uploads/2014/08/6.png)
![alt tag](http://burakicel.com/wp-content/uploads/2014/08/7.png)





Generally, the winners were Icel Bots while IcelBot2's gained penalties due to their uncertainty.

Although there were some outlier cases :

![alt tag](http://burakicel.com/wp-content/uploads/2014/08/8.png)




Looking at the results, it's evident that the IcelBot was the most successful.



Some of the patterns that can be observed from the results :

IcelBot2's are uncertain on what to do
Attackers have a major advantage, this is caused by Risk-like dice throwing. In grade 10 computer science, we made a program to observe this effect.
Robots with higher ID's usually became more successful, because of their ID's , they were the first ones to be attacked due to the algorithms of Test Robots.
It's neither good to move much or move in a very small amount of steps.
Adding many factors to the robot's algorithm is not very successful as the robot decides to attack a different person every now and then. This is backed up by our class arena fight. Robots with simplest algorithms usually became more successful than the ones that used extended data to add in many factors.

Burak Icel
