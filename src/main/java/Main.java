import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main
{
    public static String weeklyPrompts = "";

    public static void main(String[] args)
    {
        FallbackLoggerConfiguration.setDebug(true);
        String token = "";
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Add Dave to a server using: " + api.createBotInvite());
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping"))
            {
                event.getChannel().sendMessage("Pong! \uD83C\uDFD3");
            }
        });
        api.addMessageCreateListener(new Prompts());
        api.addMessageCreateListener(new ChangePrompts());
        api.addMessageCreateListener(new Roles());
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().toLowerCase().contains("thanks dave") || event.getMessageContent().contains("thank you dave") || event.getMessageContent().contains("thank you, dave") )
            {
                event.addReactionsToMessage("❤");
            }
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!whoisdave"))
                event.getChannel().sendMessage("https://i.imgur.com/wfEfZq6.gifv");
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!hank")|| event.getMessageContent().equalsIgnoreCase("!intro") )
                event.getChannel().sendMessage("https://youtu.be/U1dirHGODpM");
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!welcome"))
                event.getChannel().sendMessage("Welcome to the server! I'm Dave Green, your friendly neighborhood robot! ❤" +
                        "I can tell you what the current prompts are, give you a link to our website, or a gratitude reminder. " +
                        "Feel free to say Hi in #general, or start answering some prompts in #prompt-responses! " +
                        "You can use !help in #davebot to start!");
        });
        api.addServerMemberJoinListener(event -> {
            if (api.getTextChannelById("738405275899527209").isPresent())
            {
                //    api.getUserById("267378817599930380").get().openPrivateChannel().get().sendMessage("someone joined. in the if statement");
                api.getTextChannelById("738405275899527209").get().sendMessage("Welcome to the server! I'm Dave Green, your friendly neighborhood robot! ❤" +
                        " I can tell you what the current prompts are, give you a link to our website, or a gratitude reminder." +
                        " Feel free to say Hi in #general, or start answering some prompts in #prompt-responses! You can use !help in #davebot to see what I can do!");
            }
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!daveinfo"))
                event.getChannel().sendMessage("Dave is created in Java using the Javacord library, and was created by Dan! Dave's code can be found at https://github.com/emmyF18/DaveBot. " +
                        "If you have any ideas or questions about Dave, feel free to ask them!");
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!help"))
                event.getChannel().sendMessage("All of Dave's Commands:\n" +
                        "!prompts : Shows the weekly prompts\n" +
                        "!gratitude : Asks you what you are grateful for today\n" +
                        "!website : gives you a link to the website\n" +
                        "!ping : plays ping-pong with you in order to make sure Dave is working\n" +
                        "!whoisdave: shows gif of john talking about Dave\n" +
                        "!welcome: displays welcome message for new members\n" +
                        "!daveinfo: about message showing info about Dave.\n" +
                        "!choose: Picks between 2 or more items separated by comma\n" +
                        "!8ball: Can answer yes/no questions\n"+
                        "!hug: Gives you a (virtual) hug\n"+
                        "!rolelist\n"+
                        "! addrole <role>\n"+
                        "!help: shows this message.");

        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().startsWith("!choose"))
            {
                String fullcommand = event.getMessageContent().substring(7);
                String[] list = fullcommand.split(",");
                if(list.length > 1)
                {
                    Random random = new Random();
                    event.getChannel().sendMessage("I choose, " + list[random.nextInt(list.length)] + "!");
                }
                event.getChannel().sendMessage("You need at least 2 items to pick between!");
            }
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().startsWith("!hug"))
            {
                event.getChannel().sendMessage("*squeezes you in large hug*");
                event.getMessage().addReaction("\uD83E\uDD17");
            }
        });
        api.addMessageCreateListener(Event -> {
            if(Event.getMessageContent().startsWith("!8ball"))
            {
                String[] responses = {    "It is certain.", "It is decidedly so.", "Without a doubt", "Yes – definitely",
                        "You may rely on it", "As I see it, yes.", "Most likely.", "Signs point to yes.", "Ask again later.",
                        "Better not tell you now.", "Cannot predict now.",
                        "Don't count on it.", "My reply is no.", "My sources say no.", " Outlook not so good.", "Very doubtful." };
                Random random = new Random();
                Event.getChannel().sendMessage(responses[random.nextInt(responses.length)] );
            }
        });
        //if(LocalTime.now() == LocalTime.NOON)
        //{
         //   api.getChannelById("712800358912622623").get().asTextChannel().get().sendMessage("@GratitudeReminder Hello! Have you done your journaling today?");
        //}
    }
}
