package org.example.telegramBot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Contact;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser extends Contact{
    private boolean isAdmin;
}
