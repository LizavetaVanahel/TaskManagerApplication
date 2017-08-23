package com.example.vanahel.tasksmanagerapplication.contracts;

public interface TaskArrayAdapterContract {

    interface View {
    }

    interface Presenter {
        void onMenuButtonClicked( android.view.View view );
    }

    interface Menu {

        void edit();

        void delete();

        void addToFavorite();

        void removeFromFavorite();

        void share();

    }
}
