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
        assignableRoles.add("Purple");
        assignableRoles.add("Pink");
        assignableRoles.add("Blue");
        assignableRoles.add("Green");
        assignableRoles.add("Red");
        assignableRoles.add("Yellow");
        assignableRoles.add("White");
        assignableRoles.add("Gratitude Notification Squad");
        for (String roles:assignableRoles)
        {
            allroles = allroles +" " + roles;
        }
    }
    @Override
    public void onMessageCreate(MessageCreateEvent event)
    {
        if (event.getMessageContent().contains("!addrole"))
        {
            Optional<User> user = event.getMessageAuthor().asUser();
            String newRole = event.getMessageContent().substring(9);
            if(assignableRoles.contains(newRole) && user.isPresent() && event.getServer().isPresent() && !event.getServer().get().getRolesByNameIgnoreCase(newRole).isEmpty())
            {
                Role role = event.getServer().get().getRolesByNameIgnoreCase(newRole).get(0);
                System.out.println("role:"+ role.toString());
                System.out.println("user:"+ user.get().toString());
                role.addUser(user.get());
                //role.addUser(event.getMessageAuthor().asUser().get());
                //user.get().addRole(role);
                event.getChannel().sendMessage("Role added!");
            }
            else
            {
                event.getChannel().sendMessage("Role could not be added. Is the spelling correct?" +
                                                       " You can use !roles to see all assignable roles");
            }

        }
        if(event.getMessageContent().equalsIgnoreCase("!roles"))
        {
            event.getChannel().sendMessage("List of roles:" + allroles);
        }
    }
}
