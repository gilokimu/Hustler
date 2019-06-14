package me.gilo.side.di;

import me.gilo.side.ui.user.onboarding.SignInActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * <p>
 * Injects {@link android.app.Activity}s
 */

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract SignInActivity contributesChatActivity();



}
