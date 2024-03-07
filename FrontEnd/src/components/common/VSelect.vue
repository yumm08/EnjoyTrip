<script setup>
import { ref, computed, watch } from "vue";
const props = defineProps({ selectOption: Array });
const emit = defineEmits(["onKeySelect"]);

const key = ref("");

const formattedOptions = computed(() => {
	return props.selectOption.map((option) => ({
		label: option.text,
		value: option.value,
	}));
});

watch(key, (newValue) => {
	console.log(newValue);
	emit("onKeySelect", newValue);
});
</script>

<template>
	<!-- <select v-model="key" class="form-select form-select-sm w-50" @change="onSelect">
        <option v-for="option in selectOption" :key="option.value" :value="option.value"
            :disabled="option.value === '' ? true : false">
            {{ option.text }}
        </option>
    </select> -->
	<div class="custom-qselect-wrapper">
		<q-select
			v-model="key"
			:options="formattedOptions"
			label="Choose an option"
			emit-value
			map-options
			option-label="label"
			option-value="value"
			class="custom-qselect"
		/>
	</div>
</template>

<style scoped>
.custom-qselect-wrapper {
	height: 40px; /* 원하는 높이로 조절 */
	width: 100%; /* 부모 컨테이너에 맞게 확장 */
	display: flex; /* 부모 컨테이너 내에서의 정렬을 위해 flex 사용 */
	align-items: center; /* 수직 정렬을 위해 가운데 정렬 설정 */
	padding-top: 20px;
}

.custom-qselect {
	flex: 1; /* 자식 요소가 공간을 채우도록 설정 */
}
</style>
