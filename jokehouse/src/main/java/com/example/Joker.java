package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joker {
    List<String> jokes;

    public Joker() {
        jokes= new ArrayList<>();
        jokes.add("They used to pick on me. I was the only Jewish kid in this redneck town. They hated Jews, but they didn't know anything about them. 'Hey, Jew boy, go back to Utah!'\n");
        jokes.add("My grandma sent me to karate class at the Jewish community center because it was free. Sensei Master Rabbi Rabinowitz -- that guy was the Hebrew nightmare.");
        jokes.add("My wife and I did the Jewish divorce custom where we took a broken glass and we put it back together.");
        jokes.add("Jeff Dunham: Dear Mom, Happy Mother's Day? Peanut: Thanks for having me. And sorry I ruined your body.");
        jokes.add("My husband is Jewish. I know -- a Catholic and a Jew, right? Our kids are gonna be cashews.");
    }

    public String makeMeLaugh() {
        return jokes.get(new Random().nextInt(jokes.size()));
    }

    public List<String> getJokes() {
        return jokes;
    }


}
