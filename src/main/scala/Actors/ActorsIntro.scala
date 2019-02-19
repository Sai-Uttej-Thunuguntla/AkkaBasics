package Actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntro extends App {

  // part1 - actor systems
  val actorSystem = ActorSystem("firstActorSystem")
  println(actorSystem.name)

  //part2 - create Actors
  class LeafVillage extends Actor {
    //internal data
    var ninjaCount = 0

    //behaviour
    override def receive: Receive = {
      case msg:String => {
      ninjaCount += msg.split(" ").length
      println(s"The number of ninjas are $ninjaCount")
      }
      case _ => println("Unknown ninjas")
    }
  }

  //part3 - instanstinate Actors
  val naruto = actorSystem.actorOf(Props[LeafVillage], "naruto")

  //part4 - send msgs
  naruto ! "Hinata Sauske Itachi"
  naruto ! "Sakura Ino"


//companion object for methid2
  object Person {
  def props(name:String) = Props(new Person(name))
  }

  //Actors with parameters
  class Person(name: String) extends Actor {
    override def receive: Receive = {
      case msg:String => println(s"Hi my name is $name")
      case _ => println("Unknow msg")
    }
  }

  //instantinate - method 1
  val person1 = actorSystem.actorOf(Props(new Person("Bob")))
  person1 ! "Hey"

  //Method2
  val person2 = actorSystem.actorOf(Person.props("Bob"))
  person2 ! "Hello"
  }

