import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;


public class Main
{
    public static String weeklyPrompts ="What’s your favorite outfit? What’s a book that’s affected you in a lasting way? What  are  you  grateful  for today? " +
            "What’s your thoughts on religion, and what’s something you put your faith in? What’s a joke that made you laugh this week?";
    public static void main(String args[])
    {
       FallbackLoggerConfiguration.setDebug(true);
       String token = args[0];
       DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
       System.out.println("Add Dave to a server using: "+ api.createBotInvite());
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping"))
            {
                event.getChannel().sendMessage("Pong!");
            }
        });
       api.addMessageCreateListener(new Prompts());
       api.addMessageCreateListener(new ChangePrompts());
       api.addMessageCreateListener(event -> {
           if(event.getMessageContent().contains("Thanks Dave") || event.getMessageContent().contains("Thank you Dave"))
           {
               event.addReactionsToMessage("❤");
           }
       });
       api.addMessageCreateListener(event -> {
           if(event.getMessageContent().equalsIgnoreCase("!whoisdave"))
               event.getChannel().sendMessage("https://i.imgur.com/wfEfZq6.gifv");
       });
       api.addMessageCreateListener(event -> {
           if(event.getMessageContent().equalsIgnoreCase("!welcome"))
               event.getChannel().sendMessage("Welcome to the server! I'm Dave Green, your friendly neighboorhood robot! ❤" +
                       "I can tell you what the current prompts are, give you a link to our website, or a gratitude reminder. " +
                       "You can see everything I can do with !help");
       });
       api.addServerJoinListener(event -> {
            event.getApi().getServerTextChannelById("general").get().sendMessage("Welcome to the server! I'm Dave Green, the third Green brother and your friendly neighborhood robot! ❤ " +
                    "I can tell you what the current prompts are, give you a link to our website, or a gratitude reminder. You can see everything I can do with !help");
       });
        api.addMessageCreateListener(event -> {
            if(event.getMessageContent().equalsIgnoreCase("!daveinfo"))
                event.getChannel().sendMessage("Dave is created in Java using the Javacord library, and was created by Dan! Dave's code can be found at https://github.com/emmyF18/DaveBot." +
                        "If you have any ideas or questions about Dave, feel free to message them.");
        });
        api.addMessageCreateListener(event -> {
            if(event.getMessageContent().equalsIgnoreCase("!help"))
                event.getChannel().sendMessage("All of Dave's Commands:\n" +
                        "!prompts : Shows the weekly prompts\n" +
                        "!gratitude : Asks you what you are grateful for today\n" +
                        "!website : gives you a link to the website\n" +
                        "!ping : plays ping-pong with you in order to make sure Dave is working\n" +
                        "!whoisdave: shows gif of john talking about Dave\n" +
                        "!welcome: displays welcome message for new members\n" +
                        "!daveinfo: about message showing info about Dave.\n"+
                        "!help: shows this message.");

        });
    }
}
