package com.davidk1412.mypetdirectory.specification;

import com.davidk1412.mypetdirectory.entity.Color;
import com.davidk1412.mypetdirectory.entity.Pet;
import com.davidk1412.mypetdirectory.entity.PetSize;
import com.davidk1412.mypetdirectory.entity.PetType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;

public class PetSpecification {

    public static Specification<Pet> hasType(PetType type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("type"), type);
    }

    public static Specification<Pet> hasSize(PetSize size) {
        return (root, query, cb) ->
                size == null ? null : cb.equal(root.get("size"), size);
    }

    public static Specification<Pet> hasLocality(Integer localityId) {
        return (root, query, cb) ->
                localityId == null ? null : cb.equal(root.get("locality").get("id"), localityId);
    }

    public static Specification<Pet> hasColor(Integer colorId) {
        return (root, query, cb) -> {
            if (colorId == null) return null;
            Join<Pet, Color> colorJoin = root.join("colors");
            return cb.equal(colorJoin.get("id"), colorId);
        };
    }

    public static Specification<Pet> hasNameOnTagLike(String nameFragment) {
        return (root, query, cb) -> {
            if (nameFragment == null || nameFragment.trim().isEmpty()) return null;
            return cb.like(cb.lower(root.get("nameOnTag")), "%" + nameFragment.toLowerCase() + "%");
        };
    }

    public static Specification<Pet> isDelivered(Boolean delivered) {
        return (root, query, cb) ->
                delivered == null ? cb.equal(root.get("delivered"), false) : cb.equal(root.get("delivered"), delivered);
    }
}