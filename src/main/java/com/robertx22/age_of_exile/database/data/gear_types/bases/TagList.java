package com.robertx22.age_of_exile.database.data.gear_types.bases;

import com.google.gson.JsonObject;
import com.robertx22.age_of_exile.aoe_data.datapacks.JsonUtils;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType.SlotTag;
import com.robertx22.library_of_exile.registry.serialization.ISerializable;

import java.util.*;
import java.util.stream.Collectors;

public class TagList implements ISerializable<TagList> {


    public interface ITagString {
        String getTagId();
    }

    public TagList(List<SlotTag> tags) {
        this.tags = tags.stream()
                .map(x -> x.name())
                .collect(Collectors.toSet());
    }

    public TagList(SlotTag... tags) {
        this.tags = new HashSet<>();
        for (SlotTag tag : tags) {
            this.tags.add(tag.name());
        }
    }

    public Set<String> tags;

    public boolean contains(String tag) {
        return tags.contains(tag);
    }

    public boolean contains(ITagString tag) {
        return tags.contains(tag.getTagId());
    }

    public void add(ITagString t) {
        tags.add(t.getTagId());
    }

    public SlotFamily getFamily() {
        Optional<SlotTag> opt = tags.stream()
                .filter(f -> Arrays.stream(SlotTag.values())
                        .anyMatch(m -> m.name()
                                .equals(f)))
                .map(x -> SlotTag.valueOf(x))
                .filter(t -> t.family != SlotFamily.NONE)
                .findFirst();

        if (!opt.isPresent()) {
            System.out.println("gear type doesn't have a slot family tag!!!");
            return null;
        } else {
            return opt.get().family;
        }

    }


    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("tags", JsonUtils.stringListToJsonArray(new ArrayList<>(this.tags)));

        return json;
    }

    @Override
    public TagList fromJson(JsonObject json) {
        TagList list = new TagList();

        List<String> l = JsonUtils.jsonArrayToStringList(json.getAsJsonArray("tags"));
        list.tags = new HashSet<>(l);
        return list;

    }
}
