CREATE TABLE "artist" (
    "id"  INTEGER NOT NULL,
    "name"  TEXT(255) NOT NULL,
    "genre"  TEXT(255),
    "rating"  INTEGER,
    "comment"  TEXT,
    PRIMARY KEY ("id" ASC),
    UNIQUE ("name" ASC)
);

CREATE TABLE "album" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_artist"  INTEGER NOT NULL,
    "name"  TEXT(255) NOT NULL,
    "year"  INTEGER,
    "time"  TEXT,
    "reference_cover"  TEXT(255),
    "rating"  INTEGER,
    "comment"  TEXT,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_artist") REFERENCES "artist" ("id") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE "song" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_album"  INTEGER,
    "id_artist"  INTEGER,
    "name"  TEXT(255),
    "track"  INTEGER,
    "lyrics"  TEXT,
    "time"  TEXT,
    "rating"  INTEGER,
    "comment"  TEXT,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_album") REFERENCES "album" ("id") ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT "fkey1" FOREIGN KEY ("id_artist") REFERENCES "artist" ("id") ON DELETE CASCADE ON UPDATE CASCADE
);





