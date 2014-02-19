package chat

import akka.contrib.pattern.DistributedPubSubExtension
import akka.actor.ActorSystem
import akka.actor.Props

object MBus{
  
  def main(args: Array[String]) {
      val system = ActorSystem("testActors")
      val mediator = DistributedPubSubExtension(system).mediator
	  val subscriber =  system.actorOf(WebHookSubscriber.props(mediator), "subscriber");
	  val publisher = system.actorOf(WebhookPublisher.props(mediator), "publisher")
  }
}