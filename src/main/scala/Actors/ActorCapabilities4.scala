package Actors

import akka.actor.{Actor, ActorSystem, Props}


/* Summary:-
  Messages that are sent to actors can be of any type
  Each actor has its own context
  It can also send messages to itself using self ! "message"
 */


object ActorCapabilities4 extends App {

  //creating the test classes to test the actors capabilities
  case class myClass(myContent:String)

  case class myContext(message:String)

  case class callMyself(dummyMessage:String)

  class SimpleActor extends Actor{

    override def receive: Receive = {

      case message:String => println(s"[SimpleActor] I have received string message $message \n \n")
      //It can receive numbers and other data types as well
      case number :Int => println(s"[SimpleActor] I have received the number message  $number \n \n")
      //We can also match with our own case classes
      case userDefined:myClass => println(s"[SimpleActor] I have received the user defined case class message ---> ${userDefined.myContent} \n \n")

      case myContext(message) => {
        println(s"[SimpleActor] I have received the message to user defined myContext class and $message ")
        println(s"My context is ${context.self}")
        //${context.self} === ${self} or $self
        println(s"My context is ${self}")
        println(s"My context is $self")
        println(s"My context path is ${context.self.path} \n \n")
      }

      case callMyself(dummyMessage) =>
        {
          println(s"$dummyMessage")
          self ! "Call to myself using self"

        }

    }
  }


  val system = ActorSystem("actorCapailities")
  val simpleActorRef = system.actorOf(Props[SimpleActor],"SimpleActor")

  //Message Types
  simpleActorRef ! "Hello testing Demo of Actor Capabilities"

  simpleActorRef ! 1234

  simpleActorRef ! myClass("Hi sent from userdefined myClass")

  /* The above examples demonstrate how messages sent to actor can be of any type String number of case class"
    Messages must be Immutable and serializable
    Better to use case classes and case objects to send messages
   */


  //context.self === this in OOPs
  simpleActorRef ! myContext("This is to know my context using context.self")

  //You can also send messages to yourself
  simpleActorRef ! callMyself("This is to demonstrate how to tell the actor(call receive)  from itself")


}