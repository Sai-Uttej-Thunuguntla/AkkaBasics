package Actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorsCommunication5 extends App {

  case class sendMessageToReceiver(ref:ActorRef,message:String)
  case class receiveMessageFromActor(message:String)
  case class wirelessCommn(ref: ActorRef, str: String)

  class DemonstrateActorCommunication extends Actor
  {
    override def receive: Receive = {

      case receiveMessageFromActor(message) =>
        {
          println(s"got message $message from ${context.sender()}")
        }

      case sendMessageToReceiver(ref,message) =>
        {
          //println(s"I am $self and I am trying to send message --> '$message' to $ref ")
          ref ! receiveMessageFromActor(message)
        }

      case wirelessCommn(ref,message) =>
        {
          //println(s"Sending the message directly to receiver using forward ")
          ref forward receiveMessageFromActor(message)
        }
    }
  }

  val actorSystem5 = ActorSystem("ActorCommn")

  val sender = actorSystem5.actorOf(Props[DemonstrateActorCommunication],"messageSender")
  val receiver = actorSystem5.actorOf(Props[DemonstrateActorCommunication], name = "messageReceiver")

  sender ! sendMessageToReceiver(receiver,s"Hi ${receiver.path}")

//  println("\n \n trying to send message from receiver actor to sender actor")
//
//  receiver ! sendMessageToReceiver(sender,s"Hi ${sender.path}")

  //deadletters now in the receiveMessageFromActor we are trying to print {context.sender()} which is unknown that is deadletters
  //Trying to send message directly to receiver from deadLetters
  receiver ! receiveMessageFromActor(s"Hi ${receiver.path}")

  //Trying to forward Hi message from deadletters to receiver via sender
  sender ! wirelessCommn(receiver,s"Hi ${receiver.path}")



}
