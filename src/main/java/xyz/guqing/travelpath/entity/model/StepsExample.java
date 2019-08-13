package xyz.guqing.travelpath.entity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StepsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StepsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("\"action\" is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("\"action\" is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("\"action\" =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("\"action\" <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("\"action\" >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("\"action\" >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("\"action\" <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("\"action\" <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("\"action\" like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("\"action\" not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("\"action\" in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("\"action\" not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("\"action\" between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("\"action\" not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andAssistantActionIsNull() {
            addCriterion("assistant_action is null");
            return (Criteria) this;
        }

        public Criteria andAssistantActionIsNotNull() {
            addCriterion("assistant_action is not null");
            return (Criteria) this;
        }

        public Criteria andAssistantActionEqualTo(String value) {
            addCriterion("assistant_action =", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionNotEqualTo(String value) {
            addCriterion("assistant_action <>", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionGreaterThan(String value) {
            addCriterion("assistant_action >", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionGreaterThanOrEqualTo(String value) {
            addCriterion("assistant_action >=", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionLessThan(String value) {
            addCriterion("assistant_action <", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionLessThanOrEqualTo(String value) {
            addCriterion("assistant_action <=", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionLike(String value) {
            addCriterion("assistant_action like", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionNotLike(String value) {
            addCriterion("assistant_action not like", value, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionIn(List<String> values) {
            addCriterion("assistant_action in", values, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionNotIn(List<String> values) {
            addCriterion("assistant_action not in", values, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionBetween(String value1, String value2) {
            addCriterion("assistant_action between", value1, value2, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andAssistantActionNotBetween(String value1, String value2) {
            addCriterion("assistant_action not between", value1, value2, "assistantAction");
            return (Criteria) this;
        }

        public Criteria andCitiesIsNull() {
            addCriterion("cities is null");
            return (Criteria) this;
        }

        public Criteria andCitiesIsNotNull() {
            addCriterion("cities is not null");
            return (Criteria) this;
        }

        public Criteria andCitiesEqualTo(String value) {
            addCriterion("cities =", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesNotEqualTo(String value) {
            addCriterion("cities <>", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesGreaterThan(String value) {
            addCriterion("cities >", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesGreaterThanOrEqualTo(String value) {
            addCriterion("cities >=", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesLessThan(String value) {
            addCriterion("cities <", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesLessThanOrEqualTo(String value) {
            addCriterion("cities <=", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesLike(String value) {
            addCriterion("cities like", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesNotLike(String value) {
            addCriterion("cities not like", value, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesIn(List<String> values) {
            addCriterion("cities in", values, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesNotIn(List<String> values) {
            addCriterion("cities not in", values, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesBetween(String value1, String value2) {
            addCriterion("cities between", value1, value2, "cities");
            return (Criteria) this;
        }

        public Criteria andCitiesNotBetween(String value1, String value2) {
            addCriterion("cities not between", value1, value2, "cities");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(Integer value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(Integer value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(Integer value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(Integer value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<Integer> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<Integer> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(Integer value1, Integer value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("distance not between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNull() {
            addCriterion("instruction is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNotNull() {
            addCriterion("instruction is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualTo(String value) {
            addCriterion("instruction =", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualTo(String value) {
            addCriterion("instruction <>", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThan(String value) {
            addCriterion("instruction >", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("instruction >=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThan(String value) {
            addCriterion("instruction <", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualTo(String value) {
            addCriterion("instruction <=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLike(String value) {
            addCriterion("instruction like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotLike(String value) {
            addCriterion("instruction not like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionIn(List<String> values) {
            addCriterion("instruction in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotIn(List<String> values) {
            addCriterion("instruction not in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionBetween(String value1, String value2) {
            addCriterion("instruction between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotBetween(String value1, String value2) {
            addCriterion("instruction not between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andRoadIsNull() {
            addCriterion("road is null");
            return (Criteria) this;
        }

        public Criteria andRoadIsNotNull() {
            addCriterion("road is not null");
            return (Criteria) this;
        }

        public Criteria andRoadEqualTo(String value) {
            addCriterion("road =", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadNotEqualTo(String value) {
            addCriterion("road <>", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadGreaterThan(String value) {
            addCriterion("road >", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadGreaterThanOrEqualTo(String value) {
            addCriterion("road >=", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadLessThan(String value) {
            addCriterion("road <", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadLessThanOrEqualTo(String value) {
            addCriterion("road <=", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadLike(String value) {
            addCriterion("road like", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadNotLike(String value) {
            addCriterion("road not like", value, "road");
            return (Criteria) this;
        }

        public Criteria andRoadIn(List<String> values) {
            addCriterion("road in", values, "road");
            return (Criteria) this;
        }

        public Criteria andRoadNotIn(List<String> values) {
            addCriterion("road not in", values, "road");
            return (Criteria) this;
        }

        public Criteria andRoadBetween(String value1, String value2) {
            addCriterion("road between", value1, value2, "road");
            return (Criteria) this;
        }

        public Criteria andRoadNotBetween(String value1, String value2) {
            addCriterion("road not between", value1, value2, "road");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("\"time\" is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("\"time\" is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("\"time\" =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("\"time\" <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("\"time\" >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"time\" >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("\"time\" <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("\"time\" <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("\"time\" in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("\"time\" not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("\"time\" between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("\"time\" not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNull() {
            addCriterion("route_id is null");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNotNull() {
            addCriterion("route_id is not null");
            return (Criteria) this;
        }

        public Criteria andRouteIdEqualTo(Long value) {
            addCriterion("route_id =", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotEqualTo(Long value) {
            addCriterion("route_id <>", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThan(Long value) {
            addCriterion("route_id >", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("route_id >=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThan(Long value) {
            addCriterion("route_id <", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThanOrEqualTo(Long value) {
            addCriterion("route_id <=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdIn(List<Long> values) {
            addCriterion("route_id in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotIn(List<Long> values) {
            addCriterion("route_id not in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdBetween(Long value1, Long value2) {
            addCriterion("route_id between", value1, value2, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotBetween(Long value1, Long value2) {
            addCriterion("route_id not between", value1, value2, "routeId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}