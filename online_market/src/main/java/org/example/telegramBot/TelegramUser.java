package org.example.telegramBot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser extends Contact{
    private boolean isAdmin;
    private Double latitude;
    private Double longitude;
    private String step;
}
