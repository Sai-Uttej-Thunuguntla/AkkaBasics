package Actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorsIntro2 extends App {

class SandVillage extends Actor {
  override def receive: Receive = {
    case "Hi" => context.sender() ! "Hi from a village Member"     //actor sending msg
    case msg: String => println(s"Received String $msg")
    case num: Int => println(s"Received Integer $num")
    case Kazekage(headName) => println(s"Received message from Kazekage $headName")
    case SendMsgToYourself(msg) => self ! msg       //sends msg to itself
    case SendMsgToActorRef(villageMember) => villageMember ! "Hi"
  }
}
case class Kazekage(head: String)
val actorSystem = ActorSystem("actorCapabilitiesDemo")
val soundVillage = actorSystem.actorOf(Props[SandVillage],"soundVillage")

//actor can receive different types of msgs
  soundVillage ! "Hi from LeafVillage"
  soundVillage ! 42
  soundVillage ! Kazekage("Gaara")

//actors have information about themselves and their context
//context.self === `this`

case class SendMsgToYourself(msg: String)
soundVillage ! SendMsgToYourself("Hi to myself")

//actors can reply to the messages
val kabuto = actorSystem.actorOf(Props[SandVillage],"kabuto")
val karin = actorSystem.actorOf(Props[SandVillage], "karin")

case class SendMsgToActorRef(ref: ActorRef)

kabuto ! SendMsgToActorRef(karin)
}
