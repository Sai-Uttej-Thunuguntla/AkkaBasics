/* Small Working code of Actor Sytem
1. Create an actorSystem using ActorSystem
2. Create Actors extending Actor ase class
3. implement receive function to return the word count of the string message passed
4. Instantiate Actors using actorOf() get the actor ref and call the receive method using ! method


*/
package Actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntroAsynch2 extends App {

  // part1 - actor systems
  val actorSystem = ActorSystem("firstActorSystem")

  //Create Actors
  class wordCountActor extends Actor{

    //internal data
    var totalWords = 0

    //behaviour
    // You can simply use Receive instead of Partial Function
    def receive :Receive = {

      case message:String =>
        println(s"Received Message ${message}")
        totalWords+= message.split(" ").length
        println(s"Word count is ${totalWords}")

      case msgUnknown:Any => println(s"Can not understand ${msgUnknown}")
    }
  }

  //Instantiate Actors
  val wordCountActor = actorSystem.actorOf(Props[wordCountActor],"wordCounter")
  wordCountActor ! "Hi There Testing Actor Functionality"

  //The name cant be same for both the actors since actors are uniquely identified
  val wordCountActorTestAsynch = actorSystem.actorOf(Props[wordCountActor],"wordCounterSecond")
  wordCountActorTestAsynch ! "Hi This is second actor calling"



}

