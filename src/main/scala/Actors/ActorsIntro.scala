package Actors

import akka.actor.{Actor, ActorSystem}

object ActorsIntro extends App {

  // part1 - actor systems
  val actorSystem = ActorSystem("firstActorSystem")
  println(actorSystem.name)


  }

