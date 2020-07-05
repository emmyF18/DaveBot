import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;


public class Prompts implements MessageCreateListener
{
    @Override
    public void onMessageCreate(MessageCreateEvent event)
    {

        if (event.getMessageContent().equalsIgnoreCase("!gratitude"))
        {
            event.getChannel().sendMessage("What are you grateful for today? Tell us in  #gratitude");
        }
        if(event.getMessageContent().equalsIgnoreCase("!website"))
        {
            event.getChannel().sendMessage("Check us out online at https://journalsallthewaydowndotcom.wordpress.com");
        }
        if (event.getMessageContent().equalsIgnoreCase("!prompts"))
        {
            event.getChannel().sendMessage("This weeks prompts are: " + Main.weeklyPrompts + ". Feel free to talk about these prompts in #prompt-responses!");
        }
    }

}
