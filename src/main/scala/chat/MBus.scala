package chat

import akka.contrib.pattern.DistributedPubSubExtension
import akka.actor.ActorSystem
import akka.actor.Props

object MBus{
  
  def main(args: Array[String]) {
      val system = ActorSystem("testActors")
	  val subscriber =  system.actorOf(Props[WebHookSubscriber], "subscriber");
	  val publisher = system.actorOf(Props[WebhookPublisher], "publisher")
  }
}