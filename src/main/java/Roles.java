import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Roles implements MessageCreateListener
{
    private List<String> assignableRoles = new ArrayList<>();
    private String allroles = "";
    public Roles()
    {
        assignableRoles.add("purple");
        assignableRoles.add("pink");
        assignableRoles.add("blue");
        assignableRoles.add("green");
        assignableRoles.add("red");
        assignableRoles.add("yellow");
        assignableRoles.add("white");
        assignableRoles.add("gratitude notification squad");
        StringBuilder stringBuilder = new StringBuilder(allroles);
        for (String roles:assignableRoles)
        {
            stringBuilder.append("\n").append(roles);
            //allroles = allroles + "\n " + roles;
        }
        allroles = stringBuilder.toString();
    }
    @Override
    public void onMessageCreate(MessageCreateEvent event)
    {
        if (event.getMessageContent().contains("!addrole"))
        {
            Optional<User> user = event.getMessageAuthor().asUser();
            String newRole = event.getMessageContent().substring(9).toLowerCase();
            if(assignableRoles.contains(newRole) && user.isPresent() && event.getServer().isPresent() && !event.getServer().get().getRolesByNameIgnoreCase(newRole).isEmpty())
            {
                Role role = event.getServer().get().getRolesByNameIgnoreCase(newRole).get(0);
                role.addUser(user.get());
                event.getChannel().sendMessage("Role added!");
            }
            else if(newRole.contains("gratitude")&& user.isPresent() && event.getServer().isPresent())
            {
                Role role = event.getServer().get().getRolesByNameIgnoreCase("gratitude notification squad").get(0);
                System.out.println("role:"+ role.toString());
                System.out.println("user:"+ user.get().toString());
                role.addUser(user.get());
                event.getChannel().sendMessage(" "+"Role added!");
            }
            else
            {
                event.getChannel().sendMessage("Role could not be added. Is the spelling correct?" +
                                                       " You can use !roles to see all assignable roles");
            }

        }
        if(event.getMessageContent().equalsIgnoreCase("!roles"))
        {
            event.getChannel().sendMessage("Here are all of the assignable roles! \n" + allroles);
        }
    }
}
