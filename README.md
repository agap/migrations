![Bintray](https://img.shields.io/bintray/v/agap/maven/migrations) ![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

Simple validation of the database's migration code in your Android projects.

# Installation

Make sure your project's `build.gradle` defines access to JCenter Repository:

```groovy
buildscript {
    repositories {
        jcenter()
    }
}
```

Add the following dependency to your app module's `build.gradle` file:

```groovy
androidTestImplementation 'aga.android:migrations:(insert latest version)'
```

# Usage

This project aims to automate one quite simple, but rather important task: how to validate that your old users get the same database schema as the new users, who just did a clean install of your app?

This question arises when you use frameworks like Requery that can generate the entire schema of the database for the new users based on your `@Entity` objects. But, when it comes to migrations, you need to write migration queries yourself and validate that you don't end up messing it up.

With that being said, here's how you can automate it with Migrations.

# Code

Create the instance of `AndroidDatabaseSchema`:

```kotlin
val schemaExtractor = AndroidDatabaseSchema()
```

Once you have an initialized `SQLiteDatabase`, obtain its schema:

```kotlin
val newUsersSchema = schemaExtractor.extract(database.readableDatabase)
```

Clean up the database:

```kotlin
schemaExtractor.cleanup(database)
```

Recreate the very first state of your database that you shipped to your clients. You will need some SQL queries to do that.

After that, run the code responsible for running all your migration procedures. If you use Requery, this will be the code that you have in your `DatabaseStore#onUpgrade` method.

Once you've done that, you're ready to extract the migrated schema and compare it to the one you obtained for new users:

```kotlin
val migratedUsersSchema = schemaExtractor.extract(database.readableDatabase)
assertEquals(newUsersSchema, migratedUsersSchema)
```

That's it!

# I'm using Room or SQLDelight

That's awesome, in that case, I recommend checking out their documentation as these libraries have built-in support of migration testing. That project aims to provide some help with migration tests for the legacy projects which will not be migrated to Room/SQLDelight any time soon.

# It looks like the example misses some critical pieces...

Yeah, I recommend looking at the `DatabaseMigrationTest.kt` in the sample module.
