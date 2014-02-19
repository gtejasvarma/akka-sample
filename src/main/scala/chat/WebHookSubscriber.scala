package chat

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Subscribe
import akka.actor.ActorLogging

class WebHookSubscriber extends Actor with ActorLogging{
	
  val mediator = DistributedPubSubExtension(context.system).mediator
  mediator ! Subscribe("channel",self)
  def receive = {
    case Tick1(message:String) => log.error("from subscriber"+message)
  }
}