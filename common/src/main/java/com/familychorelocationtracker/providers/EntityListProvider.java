package com.familychorelocationtracker.providers;

import com.codename1.rad.models.AbstractEntityListProvider;
import com.codename1.rad.models.EntityList;
import com.codename1.twitterui.models.TWTUserProfile;
import com.codename1.twitterui.models.TWTUserProfileImpl;

public class EntityListProvider extends AbstractEntityListProvider {
    @Override
    public Request getEntities(Request request) {
        EntityList out = new EntityList();
        {
            TWTUserProfile profile = new TWTUserProfileImpl();
            profile.setName("Evelyn");
            out.add(profile);
        }
        {
            TWTUserProfile profile = new TWTUserProfileImpl();
            profile.setName("Liam");
            out.add(profile);
        }
        {
            TWTUserProfile profile = new TWTUserProfileImpl();
            profile.setName("Jasmin");
            out.add(profile);
        }
        {
            TWTUserProfile profile = new TWTUserProfileImpl();
            profile.setName("Lucas");
            out.add(profile);
        }
        request.complete(out);
        return request;
    }
}