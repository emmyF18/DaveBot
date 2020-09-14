import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.List;
import java.util.Optional;

public class ChangePrompts implements MessageCreateListener
{
    @Override
    public void onMessageCreate(MessageCreateEvent event)
    {
        //TODO make this role permitted
        if (event.getMessageContent().toLowerCase().startsWith("!updateprompts "))
        {
                    String fullcommand = event.getMessageContent();
                    if (fullcommand.length() > 14)
                    {
                        Main.weeklyPrompts = fullcommand.substring(15);
                        event.getChannel().sendMessage("Prompts updated!");
                    }
        }
    }
    private boolean checkRole(List<Role> roles)
    {
        System.out.println("ROLES LIST: "+ roles.toString()+ " END ROLES LIST");
        boolean correctRole = false;
        for (Role role:roles)
        {
            if(role.toString().contains("Dan"))
            {
                System.out.println("found role");
                correctRole = true;
            }
        }
        return correctRole;
    }


}
