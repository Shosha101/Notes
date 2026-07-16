# Notes App (Android / Kotlin)

A native **Android** notes app written in **Kotlin** with **MVVM** architecture.

## Features

- 📝 Create, edit, and delete notes
- 💾 Local persistence with **Room** (database + DAO)
- 🧭 Separate ViewModels per screen (add note, edit note)

## Structure

```
app/src/main/java/.../notesapp/
├── data/
│   ├── local/     # Room database + NoteDao
│   └── models/    # Note entity
└── ui/
    ├── add_note/  # view + view_model
    └── edit_note/ # view + view_model
```

## Run it

Open in Android Studio and run, or `./gradlew assembleDebug`.
