/* Small Working code of Actor Sytem
1. Create an actorSystem using ActorSystem
2. Create Actors extending Actor ase class
3. implement receive function to return the word count of the string message passed
4. Instantiate Actors using actorOf() get the actor ref and call the receive method using ! method


*/
package Actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsWithConstructorArguments3 extends App {

  // part1 - actor systems
  val actorSystem = ActorSystem("firstActorSystem")
  println(s"Name of the actor system is ${actorSystem.name}")

  //part2 - create Actors
  class Person(name: String) extends Actor {

    override def receive: Receive = {
      case message: String => println(s"Hi My Name is $name")
      case _ => println("Undefined message Received")

    }
  }

  val PersonActor = actorSystem.actorOf(Props(new Person("Uttej")))
  PersonActor ! "Hello"




  //But the above mentioned method is not a good practice

//Define a companion object and define a method that returns the Props(instantiated Actor class object)
  object PersonTest {
    def props(name:String) = Props(new PersonTest(name))

  }

  class PersonTest(name: String) extends Actor {

    override def receive: Receive = {
      case message: String => println(s"Hi My Name is $name and Message is sent using companion Object decalration")
      case _ => println("Undefined message Received")

    }
  }

  val PersonActorTest = actorSystem.actorOf(PersonTest.props("Uttej"))
  PersonActorTest ! "Hello"



}


