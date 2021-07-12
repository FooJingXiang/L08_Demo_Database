package sg.edu.rp.c346.id20011262.demodatabase;

import androidx.annotation.NonNull;

public class task {
    private int id;
    private String description;
    private String date;

    @NonNull
    @Override
    public String toString() {
        return id + "\n" + description + "\n" + date;
    }

    public task(int id, String description, String date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public int getId() { return id; }

    public String getDescription() { return description; }

    public String getDate() { return date;}

}
