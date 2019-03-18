package me.gilo.side.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.gilo.side.SideApp;

@Module
public class AppModule {

    SideApp app;

    void AppModule(SideApp application) {
        app = application;
    }

    @Provides
    @Singleton
    SideApp providesApplication() {
        return app;
    }
}
